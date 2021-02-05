package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Remove a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_removeGroup() extends KanboardApiCall[Kanboard_Response_removeGroup] {
  override val rpcMethodName: String = "removeGroup"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_removeGroup(result: Boolean)

object Kanboard_Response_removeGroup {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_removeGroup] = macroRW
}