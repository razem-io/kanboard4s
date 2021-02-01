package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Remove a user**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_removeUser(user_id: String) extends KanboardApiCall[Kanboard_Response_removeUser] {
  override val rpcMethodName: String = "removeUser"

  override val rpcParameters: Seq[(String, String)] = Seq("user_id" -> user_id)
}

case class Kanboard_Response_removeUser(result: Boolean)
