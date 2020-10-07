(ns sorting.insertion-sort)

(defn- insert [x [y & ys :as lst]]
  (if (empty? lst)
    [x]
    (if (< x y)
      (cons x lst)
      (cons y (insert x ys)))))

(defn insertion-sort [[x & xs :as lst]]
  (if (empty? lst)
    []
    (insert x (insertion-sort xs))))
