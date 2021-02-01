package model.kanboard.api

object JsonRPCRequest {
  def apply(method: String, params: (String, String)*): JsonRPCRequest = {
    JsonRPCRequest(jsonrpc = "2.0", method = method, params = params.toMap)
  }
}

case class JsonRPCRequest(id: Long = System.currentTimeMillis, jsonrpc: String, method: String, params: Map[String, String] = Map.empty)
