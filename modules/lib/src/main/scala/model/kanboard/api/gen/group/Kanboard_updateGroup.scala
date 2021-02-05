package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall

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

case class Kanboard_Request_updateGroup(group_id: String, name: String, external_id: String) extends KanboardApiCall[Kanboard_Response_updateGroup] {
  override val rpcMethodName: String = "updateGroup"

  override val rpcParameters: Seq[(String, String)] = Seq("group_id" -> group_id, "name" -> name, "external_id" -> external_id)
}

case class Kanboard_Response_updateGroup(result: Boolean)

object Kanboard_Response_updateGroup {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_updateGroup] = macroRW
}