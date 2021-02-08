package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Update a user**
* -  Parameters:
* 
*    -  **id** (integer)
*    -  **username** (string, optional)
*    -  **name** (string, optional)
*    -  **email** (string, optional)
*    -  **role** (string, optional, example: app-admin, app-manager,
*       app-user)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_updateUser(id: Int, username: Option[String] = None, name: Option[String] = None, email: Option[String] = None, role: Option[String] = None) extends KanboardApiCall[Kanboard_Response_updateUser] {
  override val rpcMethodName: String = "updateUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("id" -> IntParam(id)) ++ Seq(username.map("username" -> StringParam(_)), name.map("name" -> StringParam(_)), email.map("email" -> StringParam(_)), role.map("role" -> StringParam(_))).flatten
}

case class Kanboard_Response_updateUser(result: Boolean)

object Kanboard_Response_updateUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_updateUser] = macroRW
}