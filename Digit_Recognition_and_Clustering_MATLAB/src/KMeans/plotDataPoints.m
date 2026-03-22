function plotDataPoints(X, idx, K)
% PLOTDATAPOINTS plots data points in X, coloring them with index idx
% Since images are 400D, we plot the first 2 dimensions for visualization.
palette = hsv(K);
colors = palette(idx, :);
scatter(X(:,1), X(:,2), 15, colors);
end