package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Add a user to a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_addGroupMember(group_id: Int, user_id: Int) extends KanboardApiCall[Kanboard_Response_addGroupMember] {
  override val rpcMethodName: String = "addGroupMember"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("group_id" -> IntParam(group_id), "user_id" -> IntParam(user_id))
}

case class Kanboard_Response_addGroupMember(result: Boolean)

object Kanboard_Response_addGroupMember {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_addGroupMember] = macroRW
}