(module
  ;; Simple function that returns 42
  (func $main (result i64)
    i64.const 42
  )

  ;; Export the main function
  (export "main" (func $main))
)
