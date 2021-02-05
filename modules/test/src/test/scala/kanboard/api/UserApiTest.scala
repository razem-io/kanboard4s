package kanboard.api

import model.kanboard.api.gen.user.Kanboard_Request_createUser

import scala.concurrent.Future

class UserApiTest extends KanboardGenericTest {


  test("createUser") {
    for {
      response <- Kanboard_Request_createUser("test1", "verystrongpassword").execute
    } yield {
      response.left.foreach(println)
      response.foreach(println)

      assert(response.isRight)
      assert(response.exists(_.result == 2))
    }
  }
}
