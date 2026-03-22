function [all_theta] = oneVsAll(X, y, num_labels, lambda)
    % ONEVSALL trains multiple logistic regression classifiers
    m = size(X, 1);
    n = size(X, 2);
    all_theta = zeros(num_labels, n + 1);

    % Add ones to the X data matrix (Bias term)
    X = [ones(m, 1) X];

    % Optimization loop for each digit (1 to 10)
    for c = 1:num_labels
        initial_theta = zeros(n + 1, 1);
        options = optimset('GradObj', 'on', 'MaxIter', 50);

        % Minimize the cost function for each class
        [theta] = fmincg (@(t)(lrCostFunction(t, X, (y == c), lambda)), ...
                initial_theta, options);
        all_theta(c, :) = theta';
    end
end