(module
  (func $tee (result i64)
    (i64.const 42)
  )

  (func $main (result i32)
    (call $tee)
    (i32.wrap_i64)
  )
)
