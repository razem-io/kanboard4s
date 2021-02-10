package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Update a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **name** (string, optional)
*    -  **external_id** (string, optional)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_updateGroup(group_id: Int, name: Option[String] = None, external_id: Option[String] = None) extends KanboardApiCall[Kanboard_Response_updateGroup] {
  override val rpcMethodName: String = "updateGroup"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("group_id" -> IntParam(group_id)) ++ Seq(name.map("name" -> StringParam(_)), external_id.map("external_id" -> StringParam(_))).flatten
}

case class Kanboard_Response_updateGroup(result: Boolean)

object Kanboard_Response_updateGroup {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_updateGroup] = macroRW
}