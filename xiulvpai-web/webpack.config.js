const path = require('path');
const webpack = require('webpack');
const webpackMerge = require('webpack-merge');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const developmentConfig = require('./webpack.config.dev');
const productionConfig = require('./webpack.config.prod');

const srcdir = path.resolve(__dirname, 'src/main/assets');

const entries = {
    'javascripts/application': path.join(srcdir, 'javascripts/application.js'),
    'stylesheets/test': path.join(srcdir, 'stylesheets/test.scss'),
};

const commonConfig = {
    entry: entries,
    output: {
        publicPath: '/static/assets/'
    },
    module: {
        rules: [
            {
                test: require.resolve('jquery'),
                use: 'expose-loader?jQuery',
            },
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'postcss-loader',
                    "sass-loader", // compiles Sass to CSS, using Node Sass by default
                ]
            },
            {
                test: '/\.js$/',
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {presets: [['env', {module: false}]]}
                }
            }
        ]
    },
    optimization: {
        namedModules: true,
        noEmitOnErrors: true,
        occurrenceOrder: true,
        splitChunks: {
            cacheGroups: {
                default: false,
                vendors: false,
                common: {
                    chunks: 'initial',
                    name: 'common',
                    test: chunks => chunks.resource && !/^.*\.(css|scss)$/.test(chunks.resource) && /node_modules/.test(chunks.context)
                }
            }
        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            jQuery: 'jquery'
        })
    ]
}

module.exports = (env) => env === 'dev'
    ? webpackMerge(commonConfig, developmentConfig)
    : webpackMerge(commonConfig, productionConfig);