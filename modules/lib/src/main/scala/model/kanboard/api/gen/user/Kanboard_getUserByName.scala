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
*    -  **username** (string, required)
* 
* -  Result on success: **user properties**
* -  Result on failure: **null**
**/

case class Kanboard_Request_getUserByName(username: String) extends KanboardApiCall[Kanboard_Response_getUserByName] {
  override val rpcMethodName: String = "getUserByName"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("username" -> StringParam(username))
}

case class Kanboard_Response_getUserByName_Result(name: String, username: String, notifications_enabled: String, is_ldap_user: String, id: String, google_id: Option[String], github_id: Option[String], password: String, email: String, role: String)
object Kanboard_Response_getUserByName_Result {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getUserByName_Result] = macroRW
}
case class Kanboard_Response_getUserByName(result: Kanboard_Response_getUserByName_Result)

object Kanboard_Response_getUserByName {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getUserByName] = macroRW
}