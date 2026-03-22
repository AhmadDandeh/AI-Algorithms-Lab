function plotProgresskMeans(X, centroids, previous, idx, K, i)
% PLOTPROGRESSKMEANS displays the progress of K-Means during execution.
% It plots data points, current centroids (black X), and movement history.

% Plot the data points with assigned colors
plotDataPoints(X, idx, K);

% Plot the current centroids as black 'x'
plot(centroids(:,1), centroids(:,2), 'x', ...
     'MarkerEdgeColor','k', ...
     'MarkerSize', 10, 'LineWidth', 3);

% Plot lines showing centroid movement from previous location
for j=1:size(centroids,1)
    drawLine(centroids(j, :), previous(j, :));
end

% Title showing current iteration
title(sprintf('K-Means Iteration number %d', i))

end