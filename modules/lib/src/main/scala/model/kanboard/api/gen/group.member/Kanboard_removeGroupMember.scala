package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Remove a user from a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_removeGroupMember(group_id: Int, user_id: Int) extends KanboardApiCall[Kanboard_Response_removeGroupMember] {
  override val rpcMethodName: String = "removeGroupMember"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("group_id" -> IntParam(group_id), "user_id" -> IntParam(user_id))
}

case class Kanboard_Response_removeGroupMember(result: Boolean)

object Kanboard_Response_removeGroupMember {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_removeGroupMember] = macroRW
}