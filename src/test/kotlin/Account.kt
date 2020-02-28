import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.util.*

data class Account(
    @BsonId
    val id: Id<Account> = newId(),
    val currency: Currency
)
