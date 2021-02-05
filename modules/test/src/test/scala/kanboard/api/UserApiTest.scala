package kanboard.api

import model.kanboard.api.gen.user.{Kanboard_Request_createUser, Kanboard_Request_getAllUsers, Kanboard_Request_getUserByName}

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
}
