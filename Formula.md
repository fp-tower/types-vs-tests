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
LVIC(f: A => B) = (|A| - n) * log_2 |B|
                = |A| log_2 |B| - n * log_2 |B|
```

where `n` is the number of unit tests

## How to calculate number of unit test equivalence

We are looking for `n` such as `VIC(f: A => B) = |A' => B'|`

```scala
    VIC(f: A => B) = |A' => B'|
<=> |B| ^ (|A| - n) = |B'| ^ |A'|
<=> log_2 (|B| ^ (|A| - n)) = log_2 (|B'| ^ |A'|)
<=> (|A| - n) * log_2 |B| = |A'| * log_2 |B'|
<=> |A| * log_2 |B| - n * log_2 |B| = |A'| * log_2 |B'|
<=> n * log_2 |B| = |A| * log_2 |B| - |A'| * log_2 |B'|
<=> n = |A| - |A'| * log_2 |B'| / log_2 |B|
```

this formula is not defined when `log_2 |B| = 0` which is when `|B| = 1`.
This make sense because if `|B| = 1` then `VIC(f: A => B) = 1 ^ (|A| - n) = 1`.
So there is nothing to improve.

## getDialCode

```scala
getDialCode: String => Int

|Country| = 50

LVIC(getDialCode) = (|String| - 2) * log_2 |Option[Int]|
                  = (|String| - 2) * log_2 (2 ^ 32 + 1)
                  =~ |String| * 32


LVIC(getDialCode_v2) = (|Country| - 2) * log_2 |Int|
                     = (50 - 2) * log_2 (2 ^ 32)
                     = 48 * 32
                     = 1536

LVIC(parseCountry) =  (|String| - n) * log_2 |Option[Country]|
                   =  (|String| - n) * log_2 (|Country| + 1)
                   =~ |String| * 6
```


`getDialCode` to `parseCountry` correspond to:

```scala
n = |A| - |A'| * log_2 |B'| / log_2 |B|
  = |String| - |String| * log_2 51 / log_2 2^32
  = |String| - |String| * log_2 51 / 32
  = (1 - log_2 51 / 32) * |String|
  =~ 0.82 * |String|
  =~ 0.82 * (2^16)^(2^32)
  =~ 0.82 * (2^(2^36)) unit tests
```

## compare

```scala
compare: (Char, Char) => Int

LVIC(compare) =  (|A| - n) * log_2 |B|
              =  (|(Char, Char)| - n) * log_2 |Int|
              =  (2^16 - n) * log_2 2^32
              =  (2^16 - n) * 32
              =  2^21 - 32 * n
              =~ 2 millions - 32 * n

compare_v2: (Char, Char) => Comparison

LVIC(compare_v2) =  (|A| - n) * log_2 |B|
                 =  (|(Char, Char)| - n) * log_2 |Comparison|
                 =  (2^16 - n) * log_2 3
                 =  2^16 * log_2 3 - n * log_2 3
                 =~ 103 000 - 1.58 * n
```


`compare` to `compare_v2` correspond to:

```scala
n = |A| - |A'| * log_2 |B'| / log_2 |B|
  = |(Char, Char)| - |(Char, Char)| * log_2 |Comparison| / log_2 |Int|
  = 2^16 - 2^16 * log_2 3 / log_2 2^32
  = 2^16 - 2^16 * log_2 3 / 2^5
  = 2^16 - 2^11 * log_2 3
  = 62 289
  =~ 60 000 unit tests
```
