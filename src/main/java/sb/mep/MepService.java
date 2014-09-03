package sb.mep;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class MepService extends Application<MepConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(MepService.class);
	
	private MepConfiguration configuration;

	public MepService() {
	}

	public void initialize(Bootstrap<MepConfiguration> bootstrap) {
//		bootstrap.setName("mise-en-place-app");
		bootstrap.addBundle(new ViewBundle());
//		bootstrap.addBundle(new AssetsBundle());
	}

	public void run(MepConfiguration configuration, Environment environment) {
		this.configuration = configuration;
		
//		environment.setSessionHandler(new SessionHandler());

		/* MongoDB */
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
//		Daos.addDao(new CategoryDao(db));
//		Daos.addDao(new CityDao(db));
//		Daos.addDao(new CommentDao(db));
//		Daos.addDao(new ReviewDao(db));
//		Daos.addDao(new SessionDao(db));
//		Daos.addDao(new UserDao(db));
//		Daos.addDao(new VenueDao(db));
//		Daos.addDao(new VoteDao(db));
		
		/* OAuth2 */
//		environment.addProvider(new QcAuthProvider<User>(new QcAuthenticator(), "QuantoCusta-OAuth"));
		
		/* Resources */
//		environment.addResource(new OAuthResource());
//		environment.addResource(new CommentResource());
//		environment.addResource(new SessionResource());
//		environment.addResource(new UserResource());
//		environment.addResource(new VenueResource());
//		environment.addResource(new VoteResource());
		
		/* Health checkers */
//		environment.addHealthCheck(new MongoHealthCheck(null));
		
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
