function centroids = computeCentroids(X, idx, K)
% COMPUTECENTROIDS returns the new centroids by computing the means of the
% data points assigned to each centroid.
[m n] = size(X);
centroids = zeros(K, n);

for k = 1:K
    % Find all points assigned to cluster k
    points = X(idx == k, :);
    if ~isempty(points)
        centroids(k, :) = mean(points);
    end
end
end