(ns test.test
  (:require [doo.runner :refer-macros [doo-tests]]
            [test.cljs-dynamic-resources.test]))

(enable-console-print!)

(doo-tests 
  'test.cljs-dynamic-resources.test)