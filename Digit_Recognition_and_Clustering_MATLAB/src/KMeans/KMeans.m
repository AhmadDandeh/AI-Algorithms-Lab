%% K-Means Clustering on the 50-Image Dataset
% Automatically groups the 50 images into K clusters based on pixel similarity.

fprintf('\nRunning K-Means on the 50 custom images...\n');

K = 5; % Let's try to find 5 clusters of similar handwriting
max_iters = 10;

% Initialize random centroids from the 50 images
initial_centroids = kMeansInitCentroids(X, K);

% Run K-Means
[centroids, idx] = runKMeans(X, initial_centroids, max_iters, true);

fprintf('K-Means complete. Images have been assigned to clusters.\n');