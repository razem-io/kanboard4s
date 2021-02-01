package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Create a new user authentified by LDAP**
* -  Parameters:
* 
*    -  **username** (string, required)
* 
* -  Result on success: **user_id**
* -  Result on failure: **false**
* 
* The user will only be created if he is found on the LDAP server. This
* method works only with LDAP authentication configured in proxy or
* anonymous mode.
**/

case class Kanboard_Request_createLdapUser(username: String) extends KanboardApiCall[Kanboard_Response_createLdapUser] {
  override val rpcMethodName: String = "createLdapUser"

  override val rpcParameters: Seq[(String, String)] = Seq("username" -> username)
}

case class Kanboard_Response_createLdapUser(result: Int)
