import com.lotto.database.CurrencyCodec
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.coroutine.updateOne
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.util.ObjectMappingConfiguration
import java.util.*

class CustomCodecTest {

    private val logger = LoggerFactory.getLogger(CustomCodecTest::class.java)

    @Test
    fun testCustomCodec() = runBlocking {
        ObjectMappingConfiguration.customCodecProviders.add(CurrencyCodec())

        val coroutineClient = KMongo.createClient().coroutine
        val database = coroutineClient.getDatabase("test")

        val account = Account(currency = Currency.getInstance("USD"))
        val collection = database.getCollection<Account>()

        logger.info { "Saving" }
        collection.insertOne(account)

        logger.info { "finding" }
        assert(collection.findOneById(account.id)?.currency == Currency.getInstance("USD"))

        logger.info { "Updating" }
        collection.updateOne(
            account.copy(
                currency = Currency.getInstance("EUR")
            )
        )
        Unit
    }
}
