;; https://en.wikipedia.org/wiki/Quicksort
(ns sorting.quick-sort-without-recursive)

(defn quick-sort [arr]
  (let [stack (new java.util.Stack)]
    (with-local-vars [result []]
      (.push stack arr)
      (while (not (.empty stack))
        (let [target (.pop stack)
              pivot (first target)
              res (next target)
              left (filter #(< % pivot) res)
              right (filter #(>= % pivot) res)]
          (if (and (empty? left) (empty? right))
            (var-set result (cons pivot @result))
            (do
              (.push stack left)
              (.push stack [pivot])
              (.push stack right)))))
      (remove nil? @result))))

;; test
(quick-sort [3,2,5,4,1,2])
;; => (1 2 2 3 4 5)
