(ns sorting.quick-sort)

(defn quick-sort
  [[head & tail :as values]]
  (if (empty? values)
    []
    (let [greaters (filter #(>  % head) tail)
          smallers (filter #(<= % head) tail)]
      (concat (quick-sort smallers) [head] (quick-sort greaters)))))
