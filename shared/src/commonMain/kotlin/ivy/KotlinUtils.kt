package ivy

suspend fun <A : Any, B : Any> A.letCo(transform: suspend (A) -> B): B {
  return transform(this)
}