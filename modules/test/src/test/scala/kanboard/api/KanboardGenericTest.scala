package kanboard.api

import com.dimafeng.testcontainers.{ForAllTestContainer, ForEachTestContainer, GenericContainer}
import model.kanboard.api.JsonRPCRequest
import model.kanboard.api.gen.user.Kanboard_Request_createUser
import okhttp3.{Credentials, MediaType, OkHttpClient, Request, RequestBody}
import org.scalatest.funsuite.AsyncFunSuite
import org.testcontainers.containers.wait.strategy.Wait

import scala.concurrent.Future

trait KanboardForEachTest extends KanboardGenericTest with ForEachTestContainer {
  override val container: GenericContainer = kanboardContainer
}

trait KanboardForAllTest extends KanboardGenericTest with ForAllTestContainer {
  override val container: GenericContainer = kanboardContainer
}

trait KanboardGenericTest extends AsyncFunSuite  {

  val kanboardContainer: GenericContainer = GenericContainer(
    dockerImage = "kanboard/kanboard:latest",
    exposedPorts = Seq(80),
    waitStrategy = Wait.forHttp("/")
  )

  import upickle.default._

  private def endpoint = s"http://localhost:${kanboardContainer.mappedPort(80)}/jsonrpc.php"

  private val client: OkHttpClient = new OkHttpClient()

  private val credentials: String = Credentials.basic("admin", "admin")

  private val JSON: MediaType = MediaType.get("application/json; charset=utf-8")

  private def request(request: JsonRPCRequest): Request = new Request.Builder()
    .url(endpoint)
    .header("Authorization", credentials)
    .post(RequestBody.create(JSON, write(request)))
    .build()

  protected implicit def executeRequest: JsonRPCRequest => Future[String] = r => Future {
    client.newCall(request(r)).execute().body().string()
  }
}
