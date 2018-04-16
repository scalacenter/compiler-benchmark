package scala.tools.nsc

import java.nio.file.Path

object BloopReflect {

  /**
   * Returns the bloop configuration directory for project `name`.
   *
   * This is done through reflection, because otherwise we would need to add a dependency
   * from this project to Bloop's tests. We just need to be careful to call it only once
   * per fork.
   */
  def getBloopConfigDir(name: String): Path = {
    val classLoader = getClass.getClassLoader
    val projectHelpers = classLoader.loadClass("bloop.tasks.TestUtil")
    val method = projectHelpers.getMethod("getBloopConfigDir", classOf[String])
    method.invoke(null, name).asInstanceOf[Path]
  }

}
