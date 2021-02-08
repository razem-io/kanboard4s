package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Check if a user is member of a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_isGroupMember(group_id: Int, user_id: Int) extends KanboardApiCall[Kanboard_Response_isGroupMember] {
  override val rpcMethodName: String = "isGroupMember"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("group_id" -> IntParam(group_id), "user_id" -> IntParam(user_id))
}

case class Kanboard_Response_isGroupMember(result: Boolean)

object Kanboard_Response_isGroupMember {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_isGroupMember] = macroRW
}