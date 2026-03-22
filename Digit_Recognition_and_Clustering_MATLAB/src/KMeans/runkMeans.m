function [centroids, idx] = runKMeans(X, initial_centroids, max_iters, plot_progress)
% RUNKMEANS runs the K-Means algorithm on data matrix X (50x400)
[m n] = size(X);
K = size(initial_centroids, 1);
centroids = initial_centroids;
previous_centroids = centroids;
idx = zeros(m, 1);

for i=1:max_iters
    fprintf('K-Means Iteration %d/%d...\n', i, max_iters);
    idx = findClosestCentroids(X, centroids);
    
    if plot_progress
        % Plotting only first 2 dimensions of the 400 for visual feedback
        plotProgresskMeans(X, centroids, previous_centroids, idx, K, i);
        previous_centroids = centroids;
    end
    centroids = computeCentroids(X, idx, K);
end
end