package sb.mep;


import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.sessions.HttpSessionProvider;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.mep.api.Dish;
import sb.mep.api.DishKind;
import sb.mep.api.Event;
import sb.mep.api.Preparation;
import sb.mep.client.resources.ClientEventResource;
import sb.mep.client.resources.HtmlResource;
import sb.mep.dao.Daos;
import sb.mep.dao.DishDao;
import sb.mep.dao.EventDao;
import sb.mep.resources.DishResource;
import sb.mep.resources.EventResource;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class MepApplication extends Application<MepConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(MepApplication.class);

	private MepConfiguration configuration;

	private final HibernateBundle<MepConfiguration> hibernateBundle =
			new HibernateBundle<MepConfiguration>(Dish.class, Event.class, Preparation.class, DishKind.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(MepConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	public MepApplication() {
	}

	public static void main(String[] args) throws Exception {
		new MepApplication().run(args);
	}

	public void initialize(Bootstrap<MepConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle());
		bootstrap.addBundle(new MigrationsBundle<MepConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(MepConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ViewBundle());
	}

	public void run(MepConfiguration configuration, Environment environment) {
		this.configuration = configuration;

		/* DB */
//		final DBIFactory factory = new DBIFactory();
		//		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		//		DB db = null;

		//		Class.forName("org.h2.Driver");
		//		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		//        // add application code here
		//		conn.close();

		//		try {
		//			MongoClient client = new MongoClient(configuration.getMongo().getHost(), configuration.getMongo().getPort());
		//			db = client.getDB(configuration.getMongo().getDb());
		//			//boolean auth = db.authenticate(configuration.getMongo().getUser(), configuration.getMongo().getPwd().toCharArray());
		//			
		//			MongoManaged mongoManaged = new MongoManaged(client);
		//			environment.manage(mongoManaged);
		//		} catch (UnknownHostException e) {
		//			e.printStackTrace();
		//			LOG.error("Cannot connect with the DB :(", e);
		//			System.exit(1);
		//		}

		/* DAOs */
		Daos.addDao(new EventDao(hibernateBundle.getSessionFactory()));
		Daos.addDao(new DishDao(hibernateBundle.getSessionFactory()));
//		Daos.addDao(new DishDao(hibernateBundle.getSessionFactory()));

		/* Resources */
		environment.jersey().register(new EventResource(Daos.get(EventDao.class)));
		environment.jersey().register(new DishResource(Daos.get(DishDao.class)));

		/* Client resources */
		environment.jersey().register(new HtmlResource());
		environment.jersey().register(new ClientEventResource());
		
		environment.jersey().register(HttpSessionProvider.class);
		environment.servlets().setSessionHandler(new SessionHandler());
		
//		environment.admin().
		
		/* Health checkers */
		//environment.healthChecks().(new MongoHealthCheck(null));

		/* Cache */
		//		Cache<String, String> c1 = CacheBuilder.newBuilder().build();

		//		c1.getIfPresent(key)
		//		Cache<String, String> c = new CacheBuilder<String, String>().build() 
	}

	@Override
	public String getName() {
		return "mise-en-place-app";
	}

	public MepConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(MepConfiguration configuration) {
		this.configuration = configuration;
	}

}
