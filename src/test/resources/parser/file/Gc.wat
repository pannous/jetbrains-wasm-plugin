(module
  ;; Test GC array type
  (type $my-array (array i32))

  ;; Test GC struct type
  (type $my-struct (struct
    (field i32)
    (field (mut i64))
  ))

  ;; Test reference types
  (func $test-refs
    (local $arr (ref null $my-array))
    (local $st (ref null $my-struct))

    ;; Array operations
    (array.new $my-array (i32.const 0) (i32.const 10))
    (local.set $arr)

    ;; Struct operations
    (struct.new $my-struct (i32.const 42) (i64.const 100))
    (local.set $st)
  )
)
