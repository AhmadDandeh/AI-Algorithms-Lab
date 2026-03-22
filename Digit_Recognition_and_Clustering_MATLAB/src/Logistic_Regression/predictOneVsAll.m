function pred = predictOneVsAll(all_theta, X)
    % PREDICTONEVSALL predicts the label for a trained one-vs-all classifier
    m = size(X, 1);
    X = [ones(m, 1) X]; % Add bias term

    % Calculate probability for each class and pick the highest
    probs = X * all_theta';
    [~, pred] = max(probs, [], 2);
end