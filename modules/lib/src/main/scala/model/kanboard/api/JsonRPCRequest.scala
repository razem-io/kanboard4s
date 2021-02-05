package model.kanboard.api


import model.kanboard.api.gen.user.Kanboard_Request_createUser
import upickle.default.{macroRW, ReadWriter => RW}
case class JsonRPCRequest(id: Long, jsonrpc: String, method: String, params: Map[String, String]) {
  import upickle.default._
  def toJson: String = write(this)
}

object JsonRPCRequest {
  implicit val rw: RW[JsonRPCRequest] = macroRW

  def main(args: Array[String]): Unit = {
    println(Kanboard_Request_createUser("test1", "test1").rpcRequest.toJson)
  }

  def apply(method: String, params: (String, String)*): JsonRPCRequest = {
    JsonRPCRequest(id = System.currentTimeMillis, jsonrpc = "2.0", method = method, params = params.toMap)
  }
}
