library(ggplot2)
v <- seq(1:100)
df <- expand.grid(v, v)
names(df) <- c("a", "b")
df$sign <- with(df, sign((b-1)^a - b^(a - 1)))
ggplot(df, aes(x=a, y=b, color=factor(sign))) + geom_point() + scale_color_manual(values=c("green", "black", "blue"), name="b^a") + scale_x_continuous(breaks=v) + scale_y_continuous(breaks=v)