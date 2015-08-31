# cljs-dynamic-deps
[![Build Status](https://travis-ci.org/bago2k4/cljs-dynamic-resources.svg?branch=master)]

Dynamically load JavaScript and CSS resources from your ClojureScript code.

## Overview

*cljs-dynamic-deps* helps you load JavaScript and CSS on demand. It's very easy and light weight, it uses [Prysmatic/dommy](https://github.com/Prismatic/dommy) for dom manipulation.

It's as easy as this:

```clojure
(require '[cljs-dynamic-deps.core :as cdd])
(cdd/add-script!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js")
(cdd/add-style!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css")
```

If you need advanced options:

```clojure
(cdd/add-script!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
    (fn [e] (println "Script loaded")))
```

and also:

```clojure
(cdd/add-script!
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
    (fn [e] (println "Script loaded"))
    {:type "text/my-custom-type"
     :container-sel :div#my-container-div})
```

## Test

TBD

## License

Copyright © 2015 Iacopo Carraro

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
