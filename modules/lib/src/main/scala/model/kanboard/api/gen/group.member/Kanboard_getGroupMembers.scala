package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get all members of a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
* 
* -  Result on success: **List of users**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getGroupMembers(group_id: Int) extends KanboardApiCall[Kanboard_Response_getGroupMembers] {
  override val rpcMethodName: String = "getGroupMembers"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("group_id" -> IntParam(group_id))
}

case class Kanboard_Response_getGroupMembers_Entries_Result(group_id: String, name: Option[String], notifications_filter: String, username: String, timezone: Option[String], notifications_enabled: String, user_id: String, is_project_admin: String, is_ldap_user: String, id: String, language: Option[String], disable_login_form: String, lock_expiration_date: String, gitlab_id: Option[String], nb_failed_login: String, email: Option[String], role: String)
object Kanboard_Response_getGroupMembers_Entries_Result {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getGroupMembers_Entries_Result] = macroRW
}
case class Kanboard_Response_getGroupMembers(result: Array[Kanboard_Response_getGroupMembers_Entries_Result])

object Kanboard_Response_getGroupMembers {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getGroupMembers] = macroRW
}