function [J, grad] = lrCostFunction(theta, X, y, lambda)
    % LRCOSTFUNCTION Compute cost and gradient for logistic regression with regularization
    m = length(y); % number of training examples (50 in your case)
    n = length(theta);
    J = 0;
    grad = zeros(n);

    % Hypothesis: Sigmoid of (X * theta)
    h = sigmoid(X * theta);

    % Regularized Cost Function
    theta_reg = theta(2:end);
    J = (1/m) * sum(-y .* log(h) - (1 - y) .* log(1 - h)) + (lambda/(2*m)) * sum(theta_reg .^ 2);

    % Gradient calculation
    grad = (1/m) * (X' * (h - y));
    grad(2:end) = grad(2:end) + (lambda/m) * theta_reg;

    grad = grad(:);
end