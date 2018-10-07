package analytics
import analytics.application.{TweetETLService, TweetETLServiceImpl}
import analytics.infrastructure.{TweetExtractorImpl, TweetRepositoryImpl}
import analytics.model.{TweetExtractor, TweetRepository}
import com.google.inject.AbstractModule

object Module extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[TweetExtractor]).toInstance(TweetExtractorImpl)
    bind(classOf[TweetRepository]).toInstance(TweetRepositoryImpl)

    bind(classOf[TweetETLService]).to(classOf[TweetETLServiceImpl])
  }

}
