# |A => B|

```scala
|A => B| = |B| ^ |A|
```

# VIC

```scala
VIC(f: A => B) = |B| ^ (|A| - n)
```

where `n` is the number of unit tests

# LVIC

```scala
LVIC(f: A => B) = (|A| - n) * log2 |B|
                = |A| log2 |B| - n * log2 |B|
```

where `n` is the number of unit tests

## How to calculate number of unit test equivalence

We are looking for `n` such as `VIC(f: A => B) = |A' => B'|`

```scala
    VIC(f: A => B) = |A' => B'|
<=> |B| ^ (|A| - n) = |B'| ^ |A'|
<=> log2 (|B| ^ (|A| - n)) = log2 (|B'| ^ |A'|)
<=> (|A| - n) * log2 |B| = |A'| * log2 |B'|
<=> |A| * log2 |B| - n * log2 |B| = |A'| * log2 |B'|
<=> n * log2 |B| = |A| * log2 |B| - |A'| * log2 |B'|
<=> n = |A| - |A'| * log2 |B'| / log2 |B|
```

this formula is not defined when `log2 |B| = 0` which is when `|B| = 1`.
This make sense because if `|B| = 1` then `VIC(f: A => B) = 1 ^ (|A| - n) = 1`.
So there is nothing to improve.

## getDialCode

```scala
getDialCode: String => Int

VIC(getDialCode) =  |B| ^ (|A| - n)
                 =  |Option[Int]| ^ (|String| - 3)
                 =  |Int + 1| ^ (|String| - 3)
                 =~ |Int| ^ |String|

VIC(getDialCode_v2) = |Int| ^ (|Country| - 2)
                    = |Int| ^ (2 - 2)
                    = 1

VIC(parseCountry) =  |Option[Country]| ^ (|String| - n)
                  =  (|Country| + 1) ^ (|String| - n)
                  =  3 ^ (|String| - n)
                  =~ 3 ^ |String|
```

## compare

```scala
compare: (Char, Char) => Int

LVIC(compare) =  (|A| - n) * log2 |B|
              =  (|(Char, Char)| - n) * log2 |Int|
              =  (2^16 - n) * log2 2^32
              =  (2^16 - n) * 32
              =  2^21 - 32 * n
              =~ 2 millions - 32 * n

compare_v2: (Char, Char) => Comparison

LVIC(compare_v2) =  (|A| - n) * log2 |B|
                 =  (|(Char, Char)| - n) * log2 |Comparison|
                 =  (2^16 - n) * log2 3
                 =  2^16 * log2 3 - n * log2 3
                 =~ 103 000 - 1.58 * n
```


`compare` to `compare_v2` correspond to:

```scala
n = |A| - |A'| * log2 |B'| / log2 |B|
  = |(Char, Char)| - |(Char, Char)| * log2 |Comparison| / log2 |Int|
  = 2^16 - 2^16 * log2 3 / log2 2^32
  = 2^16 - 2^16 * log2 3 / 2^5
  = 2^16 - 2^11 * log2 3
  = 62 289
  =~ 60 000 unit tests
```