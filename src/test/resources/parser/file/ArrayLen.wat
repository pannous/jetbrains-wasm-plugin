(module
  (type $string (array (mut i8)))

  (func $test
    (local $s (ref null $string))
    (local $len i32)

    ;; Test array.len instruction
    (local.set $len (array.len (local.get $s)))
  )
)
