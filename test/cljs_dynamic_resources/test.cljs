(ns test.cljs-dynamic-resources.test
  (:require [cljs.test :refer-macros [deftest async testing is are use-fixtures]]
            [dommy.core :as dommy :refer-macros [sel1 sel]]
            [cljs-dynamic-resources.core :as cdr]))

(deftest add-script-test
  (testing "add-script test"
    (let [script (cdr/add-script! "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js")]
      (set! (.-id script) "test-script-id")
      (is (not (nil? (sel1 js/document [:script#test-script-id])))))))

(deftest add-style-test
  (testing "add-style test"
    (let [style (cdr/add-style! "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css")]
      (set! (.-id style) "test-style-id")
      (is (not (nil? (sel1 js/document [:link#test-style-id])))))))

(def test-obj [{:src "//code.jquery.com/jquery-2.1.4.js"}
               {:src "//code.jquery.com/jquery-1.11.3.js"}
               {:src "//code.jquery.com/jquery-migrate-1.2.1.js"}])

(deftest add-scripts-test
  (testing "add-scripts test"
    (cdr/add-scripts! test-obj)
    (is true)))