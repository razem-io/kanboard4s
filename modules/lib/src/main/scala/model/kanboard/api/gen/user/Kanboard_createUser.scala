package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Create a new user**
* -  Parameters:
* 
*    -  **username** Must be unique (string, required)
*    -  **password** Must have at least 6 characters (string, required)
*    -  **name** (string, optional)
*    -  **email** (string, optional)
*    -  **role** (string, optional, example: app-admin, app-manager,
*       app-user)
* 
* -  Result on success: **user_id**
* -  Result on failure: **false**
**/

case class Kanboard_Request_createUser(username: String, password: String, name: Option[String] = None, email: Option[String] = None, role: Option[String] = None) extends KanboardApiCall[Kanboard_Response_createUser] {
  override val rpcMethodName: String = "createUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("username" -> StringParam(username), "password" -> StringParam(password)) ++ Seq(name.map("name" -> StringParam(_)), email.map("email" -> StringParam(_)), role.map("role" -> StringParam(_))).flatten
}

case class Kanboard_Response_createUser(result: Int)

object Kanboard_Response_createUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_createUser] = macroRW
}