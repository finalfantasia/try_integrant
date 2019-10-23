(ns app.domain.arithmetic-test
  (:require [app.domain.arithmetic :as sut]
            [clojure.test :refer [deftest is testing]])
  (:import (java.time Instant)))


(deftest add-test
  (testing "should return a string that contains time now and sum of arguments"
    (let [now (constantly (Instant/ofEpochMilli 1571792138566))
          x 1
          y 2]
      (is (= "2019-10-23T00:55:38.566Z: 3"
             (sut/add x y now))))))

