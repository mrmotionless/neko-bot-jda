package life.nekos.bot.utils

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.concurrent.CompletableFuture

object WumpDump {

    fun paste(doc: String): CompletableFuture<String> {
        return RequestUtil.request {
            url("https://feed-the-wump.us/documents")
            post(RequestBody.create(MediaType.parse("text/plain"), doc))
        }.submit()
            .thenApply { JSONObject(it.body()!!.string()) }
            .thenApply { it.getString("key") }
    }

}