function idx = findClosestCentroids(X, centroids)
% FINDCLOSESTCENTROIDS computes the centroid memberships for every example
K = size(centroids, 1);
idx = zeros(size(X,1), 1);

for i = 1:size(X,1)
    distances = sum((centroids - X(i,:)).^2, 2);
    [~, idx(i)] = min(distances);
end
end