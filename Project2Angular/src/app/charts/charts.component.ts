import { Component, OnInit } from '@angular/core';
import { Recommendation } from '../recommendation';
import { RecommendationService } from '../recommendation.service';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {
  
  recommendations: Recommendation[];

  personalChartTitle = "Recommendations for you";
  personalChartType = "PieChart";
  personalChartData;
  personalChartColNames = ['Status', 'Count'];
  personalChartOptions = {
    'width': 400,
    'height': 300
  };

  groupChartTitle = "Recommendations for all users";
  groupChartType = "BarChart";
  groupChartData;
  groupChartColNames = ['Status', 'Count'];
  groupChartOptions = {
    'width': 400,
    'height': 300
  };
  
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit() {
    this.getRecommendations();
    this.setPersonalChartData();
    this.getAllRecommendations();
    this.setGroupChartData();
  }

  getRecommendations(): void {
    this.recommendationService.getRecommendations()
        .subscribe(recommendations => this.recommendations = recommendations);
  }

  getAllRecommendations(): void {
    this.recommendationService.getAllRecommendations()
        .subscribe(recommendations => this.recommendations = recommendations);
  }

  setPersonalChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.recommendations.length; i++) {
      if (this.recommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.personalChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }

  setGroupChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.recommendations.length; i++) {
      if (this.recommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.groupChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }
}
