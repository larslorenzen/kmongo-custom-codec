package com.lotto.database

import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.codecs.configuration.CodecRegistry
import org.litote.kmongo.util.KMongoCodecProvider
import java.util.*

class CurrencyCodec : KMongoCodecProvider<Currency>, Codec<Currency> {

    override fun codec(codecRegistryProvider: () -> CodecRegistry): Codec<Currency> = this

    override fun getEncoderClass(): Class<Currency> =
        Currency::class.java

    override fun encode(writer: BsonWriter, value: Currency?, encoderContext: EncoderContext?) {
        if (value != null) {
            writer.writeString(value.currencyCode)
        } else {
            writer.writeNull()
        }
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext?): Currency =
        Currency.getInstance(reader.readString())
}
