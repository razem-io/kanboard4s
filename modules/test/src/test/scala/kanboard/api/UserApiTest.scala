package kanboard.api

import model.kanboard.api.gen.user._

class UserApiTest extends KanboardForAllTest {

  private val expectedUser2Id = 2
  private val expectedUser3Id = 3

  private val expectedUsername2 = "User2"
  private val expectedUsername3 = "User3"

  test("createUser") {
    for {
      responseTest1 <- Kanboard_Request_createUser(expectedUsername2, "verystrongpassword").execute
      responseTest2 <- Kanboard_Request_createUser(expectedUsername3, "verystrongpassword").execute
    } yield {
      assert(responseTest1.isRight)
      assert(responseTest2.isRight)
      assert(responseTest1.exists(_.result == expectedUser2Id))
      assert(responseTest2.exists(_.result == expectedUser3Id))
    }
  }

  test("getUser") {
    for {
      response <- Kanboard_Request_getUser(expectedUser2Id).execute
    } yield {
      assert(response.isRight)
      assert(response.exists(_.result.username == expectedUsername2))
    }
  }

  test("getUserByName") {
    for {
      response <- Kanboard_Request_getUserByName(expectedUsername2).execute
    } yield {
      assert(response.isRight)
      assert(response.exists(_.result.id == expectedUser2Id.toString))
    }
  }

  test("getAllUsers") {
    for {
      response <- Kanboard_Request_getAllUsers().execute
    } yield {
      response.left.foreach(println)
      response.foreach(println)

      assert(response.isRight)
      assert(response.exists(_.result.nonEmpty))
      assert(response.exists(_.result.exists(_.id == expectedUser2Id.toString)))
      assert(response.exists(_.result.exists(_.id == expectedUser3Id.toString)))
    }
  }

  test("isActiveUser") {
    for {
      response <- Kanboard_Request_isActiveUser(expectedUser2Id).execute
    } yield {
      assert(response.isRight)
      assert(response.exists(_.result))
    }
  }

  test("disableUser") {
    for {
      responseActiveBefore <- Kanboard_Request_isActiveUser(expectedUser2Id).execute
      response <- Kanboard_Request_disableUser(expectedUser2Id).execute
      responseActiveAfter <- Kanboard_Request_isActiveUser(expectedUser2Id).execute
    } yield {
      assert(responseActiveBefore.isRight)
      assert(response.isRight)
      assert(responseActiveAfter.isRight)
      assert(responseActiveBefore.exists(_.result))
      assert(!responseActiveAfter.exists(_.result))
    }
  }

  test("enableUser") {
    for {
      responseActiveBefore <- Kanboard_Request_isActiveUser(expectedUser2Id).execute
      response <- Kanboard_Request_enableUser(expectedUser2Id).execute
      responseActiveAfter <- Kanboard_Request_isActiveUser(expectedUser2Id).execute
    } yield {
      assert(responseActiveBefore.isRight)
      assert(response.isRight)
      assert(responseActiveAfter.isRight)
      assert(!responseActiveBefore.exists(_.result))
      assert(responseActiveAfter.exists(_.result))
    }
  }

  test("removeUser") {
    for {
      responseBefore <- Kanboard_Request_getUser(expectedUser2Id).execute
      response <- Kanboard_Request_removeUser(expectedUser2Id).execute
      responseAfter <- Kanboard_Request_getUser(expectedUser2Id).execute
    } yield {
      assert(responseBefore.isRight)
      assert(response.isRight)
      assert(responseAfter.isRight)
      assert(response.exists(_.result))
      assert(responseBefore.exists(_.result.username == expectedUsername2))
      assert(responseAfter.exists(_.result == null))
    }
  }


  test("updateUser") {
    for {
      responseBefore <- Kanboard_Request_getUser(expectedUser3Id).execute
      response <- Kanboard_Request_updateUser(expectedUser3Id, name = Some("Updated Name")).execute
      responseAfter <- Kanboard_Request_getUser(expectedUser3Id).execute
    } yield {
      assert(responseBefore.isRight)
      assert(response.isRight)
      assert(responseAfter.isRight)
      assert(response.exists(_.result))
      assert(responseBefore.exists(_.result.name == ""))
      assert(responseAfter.exists(_.result.name == "Updated Name"))
    }
  }
}
