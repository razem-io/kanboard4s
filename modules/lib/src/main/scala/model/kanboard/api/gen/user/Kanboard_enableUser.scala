package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Enable a user**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_enableUser(user_id: Int) extends KanboardApiCall[Kanboard_Response_enableUser] {
  override val rpcMethodName: String = "enableUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("user_id" -> IntParam(user_id))
}

case class Kanboard_Response_enableUser(result: Boolean)

object Kanboard_Response_enableUser {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_enableUser] = macroRW
}