package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Create a new group**
* -  Parameters:
* 
*    -  **name** (string, required)
*    -  **external_id** (string, optional)
* 
* -  Result on success: **link_id**
* -  Result on failure: **false**
**/

case class Kanboard_Request_createGroup(name: String, external_id: Option[String] = None) extends KanboardApiCall[Kanboard_Response_createGroup] {
  override val rpcMethodName: String = "createGroup"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("name" -> StringParam(name)) ++ Seq(external_id.map("external_id" -> StringParam(_))).flatten
}

case class Kanboard_Response_createGroup(result: Int)

object Kanboard_Response_createGroup {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_createGroup] = macroRW
}