package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get user information**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **user properties**
* -  Result on failure: **null**
**/

case class Kanboard_Request_getUser(user_id: Int) extends KanboardApiCall[Kanboard_Response_getUser] {
  override val rpcMethodName: String = "getUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("user_id" -> IntParam(user_id))
}

case class Kanboard_Response_getUser_Result(name: String, username: String, notifications_enabled: String, is_ldap_user: String, id: String, google_id: Option[String], github_id: Option[String], password: String, email: String, role: String)
object Kanboard_Response_getUser_Result {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getUser_Result] = macroRW
}
case class Kanboard_Response_getUser(result: Kanboard_Response_getUser_Result)

object Kanboard_Response_getUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getUser] = macroRW
}