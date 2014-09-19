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
import sb.mep.api.Interval;
import sb.mep.api.Preparation;
import sb.mep.client.resources.HtmlResource;
import sb.mep.dao.Daos;
import sb.mep.dao.DishDao;
import sb.mep.dao.DishKindDao;
import sb.mep.dao.EventDao;
import sb.mep.dao.IntervalDao;
import sb.mep.dao.PreparationDao;
import sb.mep.resources.DishKindResource;
import sb.mep.resources.DishResource;
import sb.mep.resources.EventResource;
import sb.mep.resources.IntervalResource;
import sb.mep.resources.PreparationResource;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class MepApplication extends Application<MepConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(MepApplication.class);

	private MepConfiguration configuration;

	private final HibernateBundle<MepConfiguration> hibernateBundle =
			new HibernateBundle<MepConfiguration>(Dish.class, Event.class, Preparation.class, DishKind.class, Interval.class) {
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
		
		/* DAOs */
		Daos.addDao(new EventDao(hibernateBundle.getSessionFactory()));
		Daos.addDao(new DishDao(hibernateBundle.getSessionFactory()));
		Daos.addDao(new DishKindDao(hibernateBundle.getSessionFactory()));
		Daos.addDao(new PreparationDao(hibernateBundle.getSessionFactory()));
		Daos.addDao(new IntervalDao(hibernateBundle.getSessionFactory()));

		/* Resources */
		environment.jersey().register(new EventResource(Daos.get(EventDao.class)));
		environment.jersey().register(new DishResource(Daos.get(DishDao.class)));
		environment.jersey().register(new DishKindResource(Daos.get(DishKindDao.class)));
		environment.jersey().register(new PreparationResource(Daos.get(PreparationDao.class)));
		environment.jersey().register(new IntervalResource(Daos.get(IntervalDao.class)));

		/* Client resources */
		environment.jersey().register(new HtmlResource());
		//environment.jersey().register(new ClientEventResource());
		
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
