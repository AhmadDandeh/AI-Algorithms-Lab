%% Machine Learning: Custom 50-Digit Recognition Suite
% This script builds a training set from 50 PNG images (0.png to 49.png),
% trains a One-vs-All Logistic Regression model, and evaluates accuracy.

clear; close all; clc

%% ============ Part 1: Path & Parameter Setup ============
addpath(genpath(pwd)); % Add all subfolders to path

input_layer_size  = 400;  % 20x20 Input Images
num_labels = 10;          % 10 labels, from 1 to 10 (0 is mapped to 10)

%% ============ Part 2: Loading & Pre-processing Images ============
fprintf('Loading 50 custom images from src/data/ for training...\n');

X = zeros(50, 400);
y = zeros(50, 1);

for i = 0:49
    img_name = sprintf('%d.png', i);
    full_img_path = fullfile('src', 'data', img_name);

    if exist(full_img_path, 'file')
        % Read image, convert to grayscale, and normalize to [0,1]
        img = imread(full_img_path);
        if size(img, 3) == 3, img = rgb2gray(img); end
        img_double = double(img) / 255;

        % Flatten 20x20 matrix into a 1x400 vector
        X(i+1, :) = img_double(:)';

        % Labeling: Image 'i.png' represents digit mod(i, 10)
        digit = mod(i, 10);
        if digit == 0, digit = 10; end % Map digit 0 to label 10
        y(i+1) = digit;
    else
        fprintf('Warning: %s not found. Check src/data/ folder.\n', img_name);
    end
end

% Visualize the dataset
fprintf('Visualizing the 50-image dataset...\n');
displayData(X);
fprintf('Program paused. Press enter to start training.\n');
pause;

%% ============ Part 3: Training One-vs-All ============
fprintf('\nTraining One-vs-All Logistic Regression...\n')

lambda = 0.01; % Low regularization for a small, specific dataset
[all_theta] = oneVsAll(X, y, num_labels, lambda);

%% ============ Part 4: Prediction & Accuracy ============
pred = predictOneVsAll(all_theta, X);
accuracy = mean(double(pred == y)) * 100;

fprintf('\nTraining Accuracy on the 50 images: %.2f%%\n', accuracy);

% Randomly test one image from the set for verification
test_idx = randi([0 49]);
p = predictOneVsAll(all_theta, X(test_idx+1, :));
fprintf('Random Test: Image %d.png | Predicted: %d | Actual: %d\n', ...
        test_idx, mod(p, 10), mod(test_idx, 10));